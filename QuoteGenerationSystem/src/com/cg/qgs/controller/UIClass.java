package com.cg.qgs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.print.attribute.standard.Chromaticity;

import org.apache.log4j.PropertyConfigurator;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Accounts;
import com.cg.qgs.model.Policy;
import com.cg.qgs.model.PolicyDetails;
import com.cg.qgs.model.PolicyQuestions;
import com.cg.qgs.model.UserRole;
import com.cg.qgs.service.IQGSService;
import com.cg.qgs.service.QGSServiceImpl;

public class UIClass {

	static String loginId = "";
	static String roleCode = "";
	static Scanner scanner = null;
	static IQGSService service = new QGSServiceImpl();

	public static Accounts getAccountDetails() {

		System.out.println("***********Welcome To ACCOUNT Creation***********");

		IQGSService service = new QGSServiceImpl();

		String insuredName = "";
		String insuredStreet = "";
		String insuredCity = "";
		String insuredState = "";
		int insuredZip = 0;
		String busSegName = "";
		String userName = "";

		scanner = new Scanner(System.in);

		boolean insuredNameFlag = false;
		boolean userNameFlag = false;
		boolean insuredStreetFlag = false;
		boolean insuredCityFlag = false;
		boolean insuredZipFlag = false;
		boolean businessSegmentFlag = false;

		do {
			switch (roleCode) {

			case "insured": {
				userName = loginId;
				userNameFlag = true;
			}
				break;

			case "admin":
				System.out.println("Enter Insured User Name");
				userName = scanner.nextLine();

				try {
					userNameFlag = service.validateUserNameForAdminOrAgent(userName);
				} catch (QGSException e) {
					System.err.println(e.getMessage());
				}

				break;

			case "agent":
				System.out.println("Enter Insured User Name");
				userName = scanner.nextLine();
				try {
					userNameFlag = service.validateUserNameForAdminOrAgent(userName);
				} catch (QGSException e) {
					System.err.println(e.getMessage());
					userNameFlag = false;
				}

				break;

			}

		} while (!userNameFlag);

		do {
			System.out.println("Enter Insured Name");
			insuredName = scanner.nextLine();
			try {
				insuredNameFlag = service.checkInsuredName(insuredName);
			} catch (QGSException e) {
				System.err.println(e.getMessage());
			}
		} while (!insuredNameFlag);

		do {
			System.out.println("Enter Insured Street");
			insuredStreet = scanner.nextLine();
			try {
				insuredStreetFlag = service.checkInsuredStreet(insuredStreet);
			} catch (QGSException e) {
				insuredStreetFlag = false;
				System.err.println(e.getMessage());
			}
		} while (!insuredStreetFlag);

		do {
			System.out.println("Enter Insured City");
			insuredCity = scanner.nextLine();
			try {
				insuredCityFlag = service.checkCity(insuredCity);
			} catch (QGSException e) {
				insuredCityFlag = false;
				System.err.println(e.getMessage());
			}
		} while (!insuredCityFlag);

		boolean stateFlag = false;

		do {
			System.out.println("Enter Insured State");
			insuredState = scanner.nextLine();
			try {
				stateFlag = service.checkState(insuredState);
			} catch (QGSException e) {
				stateFlag = false;
				System.err.println(e.getMessage());
			}
		} while (!stateFlag);

		do {
			System.out.println("Enter Insured Zip Code");
			try {
				scanner = new Scanner(System.in);
				insuredZip = scanner.nextInt();
				scanner.nextLine();

				try {
					insuredZipFlag = service.checkInsuredZip(insuredZip);
				} catch (QGSException e) {
					insuredZipFlag = false;
					System.err.println(e.getMessage());
				}
			} catch (InputMismatchException e) {
				insuredZipFlag = false;
				System.err.println("ZipCode should contain only digits");
			}

		} while (!insuredZipFlag);

		do {

			HashMap<String, String> hashMap = new HashMap<>();

			try {
				hashMap = service.getBusinessSegmentDetails();

				Set<String> businessName = hashMap.keySet();

				System.out.println("Select Business Segment Name from :");
				System.out.println(businessName);
				busSegName = scanner.nextLine();
				busSegName = busSegName.toLowerCase();
				businessSegmentFlag = service.validateBusinessSegmentName(busSegName, businessName);

			} catch (QGSException e) {
				businessSegmentFlag = false;
				System.err.println(e.getMessage());

			}
		} while (!businessSegmentFlag);

		Accounts accounts = new Accounts(insuredName, insuredStreet, insuredCity, insuredState, insuredZip, busSegName,
				userName);

		return accounts;

	}

	public static void ProfileCreation() {

		System.out.println("***********Welcome To PROFILE Creation***********");
		boolean nameFlag = false;
		boolean passwordFlag = false;
		boolean roleCodeFlag = false;
		String password = "";
		String userName = "";
		String roleCode = "";

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter user name");
			userName = scanner.nextLine();
			try {
				nameFlag = service.ValidateUserName(userName);
			} catch (QGSException e1) {
				System.err.println(e1.getMessage());
			}
		} while (!nameFlag);

		do {
			System.out.println("Enter password");
			password = scanner.nextLine();

			try {
				passwordFlag = service.ValidatePassword(password);
			} catch (QGSException e1) {
				System.err.println(e1.getMessage());
			}
		} while (!passwordFlag);

		do {
			System.out.println("Enter role code- Insured or Agent or Admin");
			roleCode = scanner.nextLine();
			roleCode = roleCode.toLowerCase();
			try {
				roleCodeFlag = service.ValidateRoleCode(roleCode);
			} catch (QGSException e1) {
				System.err.println(e1.getMessage());
			}
		} while (!roleCodeFlag);

		if (nameFlag && passwordFlag && roleCodeFlag) {
			UserRole userRole = new UserRole(userName, password, roleCode);
			try {
				service.InsertUser(userRole);
			} catch (QGSException e) {
				System.err.println(e.getMessage());
			}
		}

	}

	public static long ViewPolicyQuestions(String businessSegId, long accountNumber) throws QGSException {

		List<PolicyQuestions> policyQuestions = new ArrayList<>();
		List<PolicyDetails> policyDetailsList = new ArrayList<PolicyDetails>();
		int choice = 0;
		int i = 1;
		int totalWeightage = 0;
		double policyPremium = 0;
		long policyNumber = 0;
		boolean choiceFlag = true;
		int weightage = 0;
		String answer = "";
		try {
			//////////////////////////
			policyQuestions = service.getPolicyQuestions(businessSegId);

			for (Iterator<PolicyQuestions> iterator = policyQuestions.iterator(); iterator.hasNext();) {

				PolicyQuestions question = (PolicyQuestions) iterator.next();

				do {
					scanner = new Scanner(System.in);

					System.out.println("Question " + i + ": " + question.getPolQuesDesc());
					System.out.println("Please choose your option");
					System.out.println("1. " + question.getPolQuesAns1());
					System.out.println("2. " + question.getPolQuesAns2());
					System.out.println("3. " + question.getPolQuesAns3());

					try {
						choice = scanner.nextInt();
						scanner.nextLine();

						switch (choice) {
						case 1:
							answer = question.getPolQuesAns1();
							weightage = question.getPolQuesAns1Weightage();
							choiceFlag = true;
							break;

						case 2:
							answer = question.getPolQuesAns2();
							weightage = question.getPolQuesAns2Weightage();
							choiceFlag = true;
							break;

						case 3:
							answer = question.getPolQuesAns3();
							weightage = question.getPolQuesAns3Weightage();
							choiceFlag = true;
							break;

						default:
							System.out.println("Please choose among the given choices");
							choiceFlag = false;
							break;
						}
					} catch (InputMismatchException e) {
						choiceFlag = false;
						System.err.println("Choice should contain only digits");
					}

				} while (!choiceFlag);

				totalWeightage = totalWeightage + weightage;

				PolicyDetails policyDetails = new PolicyDetails(question.getPolQuesId(), answer);

				policyDetailsList.add(policyDetails);

				i++;

			}

			policyPremium = totalWeightage * 100;
			Policy policy = new Policy(policyPremium, accountNumber);

			try {
				service.updateProfileCreatorId(loginId, accountNumber);

				policyNumber = service.createPolicy(policy);
			} catch (QGSException e) {
				System.err.println(e.getMessage());
			}

			for (Iterator<PolicyDetails> iterator = policyDetailsList.iterator(); iterator.hasNext();) {
				PolicyDetails policyDetails = (PolicyDetails) iterator.next();

				policyDetails.setPolicyNumber(policyNumber);

				try {
					service.createPolicyDetails(policyDetails);
				} catch (QGSException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (QGSException e) {
			System.err.println(e.getMessage());
		}
		return policyNumber;

	}

	static String getBusinessSegmentId(long accountNumber) throws QGSException {

		boolean accountFlag = false;
		String busSegName = "";
		String busSegId = "";
		HashMap<String, String> hashMap = new HashMap<>();

		try {
			accountFlag = service.checkAccountNumber(accountNumber);
			if (accountFlag == true) {
				busSegName = service.fetchBusinessSegName(accountNumber);
				hashMap = service.getBusinessSegmentDetails();
				busSegId = hashMap.get(busSegName);

			}
		} catch (QGSException e) {
			throw new QGSException(e.getMessage());
		}
		return busSegId;

	}

	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");

		String inputResult = "";

		String password;

		boolean credentialFlag = false;
		boolean choiceFlag = false;
		boolean accountNumberFlag = false;

		long accountNumber = 0;
		int choice;
		System.out.println(
				"**************************************************Welcome to QUOTE GENEREATION SERVICES**************************************************");

		scanner = new Scanner(System.in);

		do {
			try {
				System.out.println("Enter Login Id : ");
				loginId = scanner.nextLine();
				System.out.println("Enter password");
				password = scanner.nextLine();
				credentialFlag = service.verifyCredentials(loginId, password);
				roleCode = service.getRoleCode(loginId);

			} catch (QGSException e) {
				System.err.println(e.getMessage());
				credentialFlag = false;
			}

		} while (!credentialFlag);

		do {
			switch (roleCode) {
			case "insured": {

				try {

					if (service.checkAccountHolder(loginId)) {

						do {
							System.out.println("Please select your choice :");
							System.out.println("1. View Policy");

							scanner = new Scanner(System.in);
							try {
								choice = scanner.nextInt();
								scanner.nextLine();
								choiceFlag = true;

								if (!(choice == 1)) {
									choiceFlag = false;
									throw new QGSException("Select from given option");
								} else {

									List<Policy> policy = new ArrayList<>();
									policy = service.quickViewPolicy(loginId);

									if (policy.isEmpty()) {
										choiceFlag = false;
										System.out.println("No Policy is bought");
									}
									for (Policy p : policy) {
										System.out.println(p);
									}

								}

							} catch (InputMismatchException e) {
								choiceFlag = false;
								System.err.println("Choice should contain only digits");
							} catch (QGSException e) {
								choiceFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!choiceFlag);
					} else {
						System.out.println("Please select your choice :");
						System.out.println("1. Create Account");
						System.out.println("2. View Policy");

						do {
							scanner = new Scanner(System.in);
							try {
								choice = scanner.nextInt();
								scanner.nextLine();

								switch (choice) {
								case 1: {

									try {
										accountNumber = service.createAccount(getAccountDetails());
									} catch (InputMismatchException e) {
										choiceFlag = false;
										System.err.println("Choice should contain only digits");
									} catch (QGSException e) {
										System.err.println(e.getMessage());
									}

									System.out.println("Congratulations!!! Your account Created with account number: "
											+ accountNumber);

								}
									break;

								case 2:
									List<Policy> policy = new ArrayList<>();
									policy = service.quickViewPolicy(loginId);

									if (policy.isEmpty()) {
										throw new QGSException("No Policy is bought");
									}
									for (Policy p : policy) {
										System.out.println(p);
									}

									break;

								default:
									throw new QGSException("Select from given option");
								}

								choiceFlag = true;

							} catch (InputMismatchException e) {
								choiceFlag = false;
								System.err.println("Choice should contain only digits");
							} catch (QGSException e) {
								choiceFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!choiceFlag);

					}

				} catch (QGSException e) {
					System.err.println(e.getMessage());
				}

			}
				break;

			case "agent": {

				do {
					try {
						scanner = new Scanner(System.in);
						System.out.println("Select from the possible options");
						System.out.println("1. Create account");
						System.out.println("2. Buy Policy");
						System.out.println("3. View Policy");

						scanner = new Scanner(System.in);
						choice = scanner.nextInt();
						scanner.nextLine();

						switch (choice) {
						case 1:

							try {
								Accounts account = getAccountDetails();
								account.setPolicyCreatorId(loginId);
								accountNumber = service.createAccount(account);
							} catch (QGSException e) {
								System.err.println(e.getMessage());
							}

							System.out.println(
									"Congratulations!!! An account is created with account number: " + accountNumber);

							break;

						case 2: {

							do {
								scanner = new Scanner(System.in);
								System.out.println("Enter account number of your insured user");
								try {
									accountNumber = scanner.nextLong();
									scanner.nextLine();

									long policyNumber = 0;
									try {
										String busSegmentId = getBusinessSegmentId(accountNumber);
										policyNumber = ViewPolicyQuestions(busSegmentId, accountNumber);
										System.out.println("Congratulations!! A policy is created with policy number "
												+ policyNumber);
										accountNumberFlag = true;
									} catch (QGSException e) {
										System.err.println(e.getMessage());
									}
								} catch (InputMismatchException e) {
									accountNumberFlag = false;
									System.err.println("Account Number should contain only digits");

								}

							} while (!accountNumberFlag);
							break;

						}
						case 3:
							List<Policy> policy = new ArrayList<>();
							policy = service.quickViewPolicyAgent(loginId);
							if (policy.isEmpty()) {

								throw new QGSException("No Policy is bought");
							}
							for (Policy p : policy) {
								System.out.println(p);
							}
							break;

						default:

							throw new QGSException("Select from given options");
						}
						choiceFlag = true;
					} catch (InputMismatchException e) {
						choiceFlag = false;
						System.err.println("Choice should contain only digits");
					} catch (QGSException e) {
						choiceFlag = false;
						System.err.println(e.getMessage());
					}
				} while (!choiceFlag);

				break;
			}

			case "admin": {
				do {

					try {
						scanner = new Scanner(System.in);

						System.out.println("Select from the possible options");
						System.out.println("1. Profile Creation");
						System.out.println("2. Create account");
						System.out.println("3. Buy Policy");
						System.out.println("4. View Policy");
						choice = scanner.nextInt();
						scanner.nextLine();

						switch (choice) {
						case 1:
							ProfileCreation();
							System.out.println("Congratulations!! Profile is created");
							break;

						case 2:
							try {
								Accounts account = getAccountDetails();
								account.setPolicyCreatorId(loginId);
								accountNumber = service.createAccount(account);
							} catch (QGSException e) {
								System.err.println(e.getMessage());
							}

							System.out.println(
									"Congratulations!!! An account is created with account number: " + accountNumber);
							break;

						case 3:

							do {
								try {
									scanner = new Scanner(System.in);
									System.out.println("Enter account number of your insured user");
									accountNumber = scanner.nextLong();
									scanner.nextLine();
									String busSegmentId = getBusinessSegmentId(accountNumber);
									long policyNumber = ViewPolicyQuestions(busSegmentId, accountNumber);
									System.out.println(
											"Congratulations!! A policy is created with policy number " + policyNumber);

									accountNumberFlag = true;
								} catch (InputMismatchException e) {
									accountNumberFlag = false;
									System.err.println("Account Number should contain only digits");
								}

							} while (!accountNumberFlag);

							break;

						case 4:

							List<Policy> policy = new ArrayList<>();
							policy = service.quickViewPolicyAdmin();
							if (policy.isEmpty()) {
								throw new QGSException("No Policy is bought");
							}
							int k = 1;
							System.out.println("Generate detailed report from the following policies:");
							for (Policy p : policy) {
								System.out.println(k + " " + p);
								k++;
							}
							boolean policyViewFlag = false;
							do {

								try {
									scanner = new Scanner(System.in);
									int input = scanner.nextInt();
									scanner.nextLine();
									if (input >= policy.size()) {
										policyViewFlag = false;
										System.err.println("Please select a valid option");
									} else {
										long policyNum = policy.get(input - 1).getPolicyNumber();
										long accNum = policy.get(input - 1).getAccountNumber();
										service.generateReport(accNum, policyNum);
										policyViewFlag = true;
									}
								} catch (InputMismatchException e) {
									policyViewFlag = false;
									System.err.println("Input should contain only digits");
								}

							} while (!policyViewFlag);

							break;
						default:
							throw new QGSException("Select from given option");

						}
						choiceFlag = true;
					} catch (InputMismatchException e) {
						choiceFlag = false;
						System.err.println("Choice should contain only digits");
					} catch (QGSException e) {
						choiceFlag = false;
						System.err.println(e.getMessage());
					}

				} while (!choiceFlag);
			}
			}
			@SuppressWarnings("unused")
			boolean doFlag = false;
			do {
				System.out.println("Do you want to continue again: (Yes/No)");
				inputResult = scanner.next();

				if (inputResult.equals("y") || inputResult.equalsIgnoreCase("yes")) {
					doFlag = true;
				}

				else if (inputResult.equals("n") || inputResult.equalsIgnoreCase("no")) {
					System.err.println("Program Exited by User");
					System.out.println(
							"*****************************************************ThankYou For Choosing Our Service****************************************************");
					System.exit(0);
				} else {
					System.err.println("Select valid  input ");
					doFlag = false;
				}
			} while (!doFlag);
		} while (inputResult.equalsIgnoreCase("yes") || inputResult.equalsIgnoreCase("y"));

		scanner.close();
	}
}
